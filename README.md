# CustomStage
A JavaFX undecorated stage which can fully be customized

[![Latest Version](https://img.shields.io/github/release/Oshan96/CustomStage.svg)](https://github.com/Oshan96/CustomStage/releases)
![](https://img.shields.io/github/license/Oshan96/CustomStage.svg)
[![Total Downloads](https://img.shields.io/github/downloads/Oshan96/CustomStage/total.svg)](https://github.com/goxr3plus/XR3Player/releases)

## Overview
This CustomStage is a JavaFX undecorated Stage. To put it simple, CustomStage is a Window and you can add different views (FXML files)
to the window (like changing the scene of the window) as you prefer.
The basic problem making the Stage "Undecorated" is that you will not be able to,
  1) Resize the window using mouse.
  2) Lose the default action buttons.
  3) Move the window (by dragging) (etc.)

So, CustomStage will get rid of all of these issues since it includes,
  1) Window resizing (the ResizeHelper class is used here with minor modifications) -> [ResizeHelper class](https://stackoverflow.com/questions/19455059/allow-user-to-resize-an-undecorated-stage)
  2) Default action buttons and their behaviour (close, maximize/restore, minimize)
  3) Window dragging 

### What else?

- Apart from those, this is called **CustomStage** since it can be customized **_as you wish_**

#### How?

- Easy. You can get your customized Stage using the [**CustomStageBuilder**](src/lk/vivoxalabs/customstage/CustomStageBuilder.java) class. 
  This class includes all the methods you will need to customize your window.

## How to use?

- Download and add as a dependancy to your project
- **Binaries** can be found at [**CustomStage binaries**](https://github.com/Oshan96/CustomStage/releases)

## How to use a CustomStage?

- An example is provided in [**How to use a CustomStage**](src/lk/vivoxalabs/customstage/test/StageTest.java) for a complete implementation

![CustomStage](https://preview.ibb.co/mJrs2x/Custom_Stage.png)

- To achieve transparency, see [**Transparent CustomStage**](src/lk/vivoxalabs/customstage/test/TransparentStage.java)

![Transparent CustomStage](https://preview.ibb.co/bWvfpc/Transparent.png)

- CustomStage with custom icons for (close,minimize,maximize/restore) buttons [**CustomStage with custom icons**](src/lk/vivoxalabs/customstage/test/CustomIconStage.java)

![CustomStage with custom icons](https://preview.ibb.co/jzJN2x/custom_Icon.png)

### Any issue detected?
![:D](https://lh3.googleusercontent.com/SVKzPc8BQlUkxqPY87sn2SGomGAxhkqRHSQDw53EhGGbth2tbebxMtiSmX7MQ3augQ=w300)

**Feel free to post issues in "Issues" for further improvements** 

