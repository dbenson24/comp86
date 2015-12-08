###
Author: Derek Benson
Date: 12/8/2015
Name: Index.coffee
###

###
Creates the Blade of a propellor
###
class Blade extends THREE.Object3D
    constructor: () ->
        super()
        @_thickness = 1
        @_width = 2.5
        @_length = 25
        
        @_blade = new THREE.Mesh(new THREE.BoxGeometry(@_thickness, @_width, @_length), new THREE.MeshLambertMaterial( color: 0x8D6E63 ) )
        @_blade.position.set @_thickness/2, 0, 0
        @add(@_blade)
###
Creates a Propellor and defines its spin
###
class Propellor extends THREE.Object3D
    constructor: () ->
        super()
        @_prop = [new Blade, new Blade]
        @_rotation = (Math.PI / @_prop.length)
        for blade, i in @_prop
            blade.rotation.set i * @_rotation, 0, 0
            @add(blade)
            
    spin: (rads) =>
        for blade in @_prop
            blade.rotation.x += rads
###
Organizes a body, wings, and Propellor into a Plane
###
class Plane extends THREE.Object3D
    constructor: () ->
        super()
        radius = 7
        length = 100
        bodyGeometry = new THREE.CylinderGeometry radius, radius, length, 200
        bodyMaterial = new THREE.MeshLambertMaterial color: 0x757575
        @_body = new THREE.Mesh bodyGeometry, bodyMaterial
        @_body.rotation.set 0, 0, Math.PI/2
        
        wingGeometry = new THREE.BoxGeometry 20, 3, 75
        wingMaterial = new THREE.MeshLambertMaterial color: 0x90A4AE
        @_wing = new THREE.Mesh wingGeometry, wingMaterial
        
        finGeometry = new THREE.BoxGeometry 15, 15, 1
        @_fin = new THREE.Mesh finGeometry, wingMaterial
        @_fin.position.set -length/2 + Math.pow(15*15 + 15*15, 0.5)/2, radius, 0
        @_fin.rotation.set 0, 0, -Math.PI/4
        
        @_prop = new Propellor
        @_prop.position.set length/2, 0, 0
        
        @add @_prop
        @add @_body
        @add @_wing
        @add @_fin
        
    spinProp: (rads) =>
        @_prop.spin(rads)
###
Creates a plane that rotates around a specific point at a certain radius
###
class RotatingPlane extends THREE.Object3D
    constructor: (radius, @speed) ->
        super()
        if(!@speed)
            @speed = 0
        @plane = new Plane
        @plane.position.set 0, 0, radius
        @propSpeed = 0.25
        @add @plane
        

    spinProp: (rads) =>
        @plane.spinProp(rads)
        
    tick: () =>
        @rotation.y += @speed
        @spinProp @propSpeed
        
class Cloud extends THREE.Object3D
    constructor: (count, radius) ->
        super()
        @_droplets = []
        
        getRandom = () ->
            (2 * radius * Math.random()) - radius
            
        callback = () =>
            dropletGeometry = new THREE.SphereGeometry 25 * radius / count, 32, 16
            dropletMaterial = new THREE.MeshBasicMaterial { map: texture, opacity: 0.7, blending: THREE.AdditiveBlending, transparent: true, color: 0xeeeeee}
                
            
            for i in [1..count]
                droplet = new THREE.Mesh dropletGeometry, dropletMaterial
                droplet.position.set getRandom(), getRandom(), getRandom()
                @_droplets.push(droplet)
                @add droplet
        
        texture = THREE.ImageUtils.loadTexture('images/cloud.png', {}, callback)
###
Creates a Mountain of a specific radius at the bottom and height
###
class Mountain extends THREE.Object3D
    constructor: (radius, height) ->
        super()
        mountainGeometry = new THREE.CylinderGeometry 0, radius, height, 40
        mountainMaterial = new THREE.MeshLambertMaterial color: 0x5D4037
        @_mountain = new THREE.Mesh mountainGeometry, mountainMaterial
        @add @_mountain

window.onload = () ->
    ###
    THREE.js Initilization
    ###
    scene = new THREE.Scene()
    camera = new THREE.PerspectiveCamera( 45, window.innerWidth / window.innerHeight, 0.1, 20000 )
    
    renderer = new THREE.WebGLRenderer({ antialiasing: true })
    if Detector.webgl
        renderer = new THREE.WebGLRenderer( { antialias: true } )
        renderer.setClearColor(new THREE.Color(0xEEEEEE))
    else 
        renderer = new THREE.CanvasRenderer();
    renderer.setSize( window.innerWidth, window.innerHeight )
    document.body.appendChild( renderer.domElement )
    
    camera.position.set(0,150,800)
    camera.lookAt(scene.position)
    
    ###
    Lighting
    ###
    light = new THREE.PointLight( 0xffffff)
    light.position.set(500,250,0)
    ambientLight = new THREE.AmbientLight(0x111111)
    skyLight = new THREE.HemisphereLight( 0xffffff, 0x080808, 1 )
    
    scene.add light
    scene.add skyLight 
    scene.add ambientLight
    
    ###
    Controls
    ###
    controls = new THREE.OrbitControls camera, renderer.domElement
    
    ###
    Object Initialization
    ###
    plane1 = new RotatingPlane 350, 0.04
    plane2 = new RotatingPlane 500, 0.025
    
    scene.add plane1
    scene.add plane2
    
    cloud1 = new Cloud 250, 50
    cloud1.position.set 350, 0, -125
    cloud2 = new Cloud 75, 50
    cloud2.position.set -150, 0, 50
    
    scene.add cloud1
    scene.add cloud2
    
    mountain = new Mountain 200, 350
    scene.add mountain
    
    ###
    Render Loop
    ###
    render = () ->
        requestAnimationFrame render 
        renderer.render scene, camera 
        plane1.tick()
        plane2.tick()
        
        cloud1.rotation.y += 0.045
        cloud2.rotation.y += 0.045
        
        cloud1.rotation.x += 0.045
        cloud2.rotation.x += 0.045
        
        
        controls.update()

    keyDownListener = (e) ->
        adjustSpeed = (ds, plane) ->
            plane.speed += ds
        switch e.keyCode
            when 87 then adjustSpeed 0.005, plane1
            when 83 then adjustSpeed -0.005, plane1
            when 69 then adjustSpeed 0.005, plane2
            when 68 then adjustSpeed -0.005, plane2
        
    document.addEventListener("keydown", keyDownListener, false)
    
    render()
    

        
        