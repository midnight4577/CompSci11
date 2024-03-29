# This is a simple implementation of a 2D ballistics calculator that includes resistance due to fluid

## Features:
- Complete customization of all ballistic variables (mass, drag coefficent, cross sectional area, fluid density)
- Customization of most simulation variables (gravity, velocity, angle, time step, y-stop)
- Ability to save and load different "Projectiles"
- Can Graph multiple projectile trajectorys

## Use:
### Getting started:
1. Go to the "Edit Ballistic Variables" tab
2. Enter a value for each variable and hit save (EX: Name: Sample Bullet, Drag coefficent: 0.3, Area: 0.0000283, Fluid density: 1.2, Mass: 0.00071)
3. Go to the "Manage Projectiles" tab
4. Select the projectile you just created
5. Go to the "View Trajectory" tab
6. Enter values for the velocity and the angle (EX: Velocity: 800, Angle: 45)
7. Press "calculate"

### Saving and loading projectiles:
#### Saving:
1. Go to the "Manage Projectiles" tab
2. Select the projectile you want to save
3. Enter a valid filename (EX: bullet)
4. Press "Save Current Projectile"

#### Loading:
1. Go to the "Manage Projectiles" tab
2. Enter the name of the file you want to load (EX: bullet.proj)
3. Press "Get Projectile"
