# Knights Armor

Knights Armor is a utility library with various Quality of Life features developed by @LoganMiller5505, 2023 alumni of Team 14365.

## Description

### Features

Knights armor contains several different modules, each with their own unique purposes:

- Delivery: Adds QOL features for better control of Motors and Servos to be used within intake and output subsystems.
- Sensors: Adds QOL features for better control of various Sensors, including LED lights.
- Utils: Adds QOL features for various base-level features, such as telemetry, gamepads, file I/O, and statistical functions.
- Vision: Adds support for a dual webcam system and enhanced pipeline control, as developed by @rovio-chen during the PowerPlay season.

Additionally, the following features may be implemented if they are deemed worth the development time compared to the existing implementations of other projects:

- Commands: Adds advanced control structures through usage of a command scheduler. Currently handled by [FTCLib](https://docs.ftclib.org/ftclib/command-base/command-system/command-scheduler), with a more in depth implementation using Team 6133's [Switchboard Scheduler](https://github.com/WHHSFTC/switchboard).
- Drive: Adds advanced trajectory generation and following. Currently handled by [Roadrunner](https://github.com/acmerobotics/road-runner).

### Usage

This library is a compilation of features that I believe would be helpful for Team 14365 going forward. While it is tailored for usage by Team 14365 specifically, all teams are welcome to look at this code and implement it within their own projects.

## Installation

Currently, to install, all these files must just exist within the TeamCode folder. In the future, once the project is in a more fully developed state, I will look into adding it as a maven extension, similar to how other prominent FTC libraries are installed.

There are also several projects that must be installed for various features of this library:

- [PhotonFTC](https://github.com/Eeshwar-Krishnan/PhotonFTC)
- [EasyOpenCV](https://github.com/OpenFTC/EasyOpenCV)
- [EOCV AprilTag Plugin](https://github.com/OpenFTC/EOCV-AprilTag-Plugin)

## Support

If there are any issues, contact @LoganMiller5505 through his email, loganmiller5505@gmail.com.