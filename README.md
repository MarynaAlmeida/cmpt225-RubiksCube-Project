# Rubiks Cube Solver - CMPT225

A Java application developed for the Data Structures and Programming (CMPT225) class that calculates solution paths for scrambled Rubik's Cubes in under 10 seconds using the **Bidirectional A\*** search algorithm.
*(Note: Developed as part of a collaborative team project at SFU)*

## Overview
The solver computes and outputs a sequence of face movements to unscramble the cube.

### Cube Notation & Mapping
* **Colors (6 Total):** 
  * `R` - Red
  * `B` - Blue
  * `G` - Green
  * `Y` - Yellow
  * `O` - Orange
  * `W` - White

* **Faces / Moves:** 
  * `L` - Left
  * `R` - Right
  * `D` - Down
  * `U` - Up
  * `F` - Front
  * `B` - Back 
  *(Note: Moves represent clockwise rotations of the respective face.)*
