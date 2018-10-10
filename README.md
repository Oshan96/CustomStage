<div style="text-align:center;display:block"><img src="https://i.imgur.com/kvAwJlm.png" width="250" height="250"></div>

# CustomStage  [![Mentioned in Awesome JavaFX](https://awesome.re/mentioned-badge.svg)](https://github.com/mhrimaz/AwesomeJavaFX)
A JavaFX undecorated stage which can fully be customized

[![Download](https://api.bintray.com/packages/oshan96/CustomStage/lk.vivoxalabs.customstage/images/download.svg) ](https://bintray.com/oshan96/CustomStage/lk.vivoxalabs.customstage/_latestVersion)
![Licence(https://img.shields.io/github/license/Oshan96/CustomStage.svg)](https://img.shields.io/github/license/Oshan96/CustomStage.svg)
[![Total Downloads](https://img.shields.io/github/downloads/Oshan96/CustomStage/total.svg)](https://github.com/Oshan96/CustomStage/releases)
[![JitPack](https://jitpack.io/v/Oshan96/CustomStage.svg)](https://jitpack.io/#Oshan96/CustomStage)
[![HitCount](http://hits.dwyl.io/Oshan96/CustomStage.svg)](http://hits.dwyl.io/Oshan96/CustomStage)

> An Implementation [See the code in wiki at "A complete implementation"](https://github.com/Oshan96/CustomStage/wiki#a-complete-implementation)

![CustomStage Implementation](https://thumbs.gfycat.com/JampackedDetailedJapanesebeetle-size_restricted.gif)

## Additional Tools provided (After v1.3.0)
- [FileLoader](https://github.com/Oshan96/CustomStage/blob/master/src/main/java/lk/vivoxalabs/scenemanager/tools/FileLoader.java)
- [SceneManager](https://github.com/Oshan96/CustomStage/blob/master/src/main/java/lk/vivoxalabs/scenemanager/SceneManager.java)

> ## Checkout the [CustomStage Wiki][wiki] for more examples and documentation.

## Using CustomStage ? 

- **Fork the repository** and update with this readme's [Projects using CustomStage](#projects-using-customstage) section in the following format adding your project details and do a **_Pull Request!_**

> Project_Name : Brief_Description

## Projects using CustomStage
- [RentLio](https://github.com/Shehanka/RentLio) : This is a vehicle reservation system. Which is made with JavaFX and also using hibernate and RMI.

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

- Window is **_automatically scaled_** as for screen resolution
- Very **_responsive_**
- Apart from those, this is called **CustomStage** since it **_can be customized as you wish_**

#### How?

- Easy. You can get your customized Stage using the [**CustomStageBuilder**](src/main/java/lk/vivoxalabs/customstage/CustomStageBuilder.java) class. 
  This class includes all the methods you will need to customize your window.

## How to use?

**Starting from version 1.3.1 CustomStage releases are/will be available through JCenter and MavenCentral**

### Maven 

```xml
<dependency>
    <groupId>lk.vivoxalabs.customstage</groupId>
    <artifactId>CustomStage</artifactId>
    <version>1.3.2</version>
</dependency>
```

### Gradle 

```
  dependencies {
    compile 'lk.vivoxalabs.customstage:CustomStage:1.3.2'
  }
```

### Download via Jitpack (Will not be possible for releases after v1.3.1)

- v1.3.1 CustomStage via JitPack (See the releases here : https://jitpack.io/#Oshan96/CustomStage)

### Gradle

> Add jitpack as a repository

```
repositories {
    maven { url 'https://jitpack.io' }
}
```

> Add dependancy

```
dependencies {
    compile 'com.github.Oshan96:CustomStage:v1.3.1'
}
```

### Maven 

> Add jitpack as a repository

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

> Add dependancy 

```xml
<dependency>
  <groupId>com.github.Oshan96</groupId>
  <artifactId>CustomStage</artifactId>
  <version>v1.3.1</version>
</dependency>
```

### Or download and add as a dependancy to your project
- **Binaries** can be found at [**CustomStage binaries**](https://bintray.com/oshan96/CustomStage/lk.vivoxalabs.customstage/_latestVersion)

## How to use a CustomStage?

- An example is provided in [**How to use a CustomStage**](examples/v1_0_0/StageTest.java) for a complete implementation

![CustomStage](https://preview.ibb.co/mJrs2x/Custom_Stage.png)

- To achieve transparency, see [**Transparent CustomStage**](examples/v1_0_0/TransparentStage.java)

![Transparent CustomStage](https://preview.ibb.co/bWvfpc/Transparent.png)

- CustomStage with custom icons for (close,minimize,maximize/restore) buttons [**CustomStage with custom icons**](examples/v1_0_0/CustomIconStage.java)

![CustomStage with custom icons](https://preview.ibb.co/jzJN2x/custom_Icon.png)

## Documentation
CustomStage API Documentation can be found here : [CustomStage Documentation](https://oshan96.github.io/CustomStage/)

### Any issue detected?
![:D](https://lh3.googleusercontent.com/SVKzPc8BQlUkxqPY87sn2SGomGAxhkqRHSQDw53EhGGbth2tbebxMtiSmX7MQ3augQ=w300)

**Feel free to post issues in "Issues" for further improvements** 

[imgLogo]: https://i.imgur.com/uV4rDEM.png

[wiki]: https://github.com/Oshan96/CustomStage/wiki

