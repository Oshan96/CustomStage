# CustomStage
A JavaFX undecorated stage which can fully be customized

## Overview
This CustomStage is a JavaFX undecorated Stage. To put it simple, CustomStage is a Window and you can add different views (FXML files)
to the window (like changing the scene of the window) as you prefer.
The basic problem making the Stage "Undecorated" is that you will not be able to,
  1) Resize the window using mouse.
  2) Lose the default action buttons.
  3) Move the window (by dragging) (etc.)

So, CustomStage will get rid of all of these issues since it includes,
  1) Window resizing (the ResizeHelper class is used here with minor modifications) -> [ResizeHelper class](https://stackoverflow.com/questions/40320199/how-to-automatically-resize-windows-in-javafx-for-different-resolutions)
  2) Default action buttons and their behaviour (close, maximize/restore, minimize)
  3) Window dragging 

### What else?

- Apart from those, this is called **CustomStage** since it cant be customized **_as you wish_**

#### How?

- Easy. You can get your customized Stage using the [**CustomStageBuilder**](src/lk/vivoxalabs/customstage/CustomStageBuilder.java) class. 
  This class includes all the methods you will need to customize your window.

## How to use?

- Download and add as a dependancy to your project 

## How to use a CustomStage?

- An example is provided in [**How to use a CustomStage**](src/lk/vivoxalabs/customstage/test/StageTest.java)

### Any issue detected?
![:D](https://lh3.googleusercontent.com/SVKzPc8BQlUkxqPY87sn2SGomGAxhkqRHSQDw53EhGGbth2tbebxMtiSmX7MQ3augQ=w300)

**Feel free to post issues in "Issues" for further improvements** 

