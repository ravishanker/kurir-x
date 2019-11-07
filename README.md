# Kurir App
The Kurir App is using Kotlin/Native to build cross-platform solution in order to share the codes on both Android and iOS.

In order to share the codes across the platforms, we are abstracting the Business logic into a separate layer.
We introduced a new framework called 

In domain layer, we are using Repository pattern with integration of Ktor Network library for HTTP transactions. And use kotlinx.serialization library to do the deserialisation from Json payload into Pojo model classes

There are 2 major packages inside the app:
### foundation
The foundation provides non-feature related implementation for reusable components, including network, repository, persist data storage and SAW framework (along with awareness package to deal with lifecycle related callback)
##
### kurir
This layer are the modules and user features without actual UI implementation integrated. All the modules have their own SAW components to reflect the feature.

##
# SAW (Scene - Actor - Wrapper) framework
[![SAW](https://github.com/crazybeanstudio/kurir-x/tree/develop/assets/saw_logo.png)]
### Scene
The pure interface definition of UI creation, like showList, showError. (Similar concept to View in MVP pattern)
### Actor
Actor holds the reference to Scene and Wrapper. It observes Emitter result provided in corresponding Worker class in order to process the result and drive the UI flow via interfaces defined in Scene. (Similar concept to Presenter to MVP pattern)
### Wrapper
Wrapper provides the Data from Repository to Adviser. It normally provides the result via Emitter class which is lifecycle awared. (Similar concept to ViewMode in MVVM pattern)

# Awareness
Awareness provides the lifecycle-like concept implementation from Android platform. To make the lifecycle callbacks consistent across Android and iOS, iOS adopts the same lifecycle callbacks method from Awareness class.

## Lifecycle callback mapping for Android and iOS
```
    
    /**
     * Lifecycle callback definition
     * onLoad: Android.Activity.onCreate()
     *         iOS.ViewController.viewDidLoad()
     */
    protected open fun onLoad(params: Map<String, Any?>) {
        Logger.d("$simpleName: onLoad")
    }

    /**
     * onRelease: Android.Activity.onDestroy()
     *            iOS.ViewController.deInit()
     */
    protected open  fun onRelease() {
        Logger.d("$simpleName: onRelease")
        owner?.awareness?.removeObserver(this)
    }

    /**
     * onAppear: Android.Activity.onStart()
     *           iOS.ViewController.viewWillAppear()
     */
    protected open  fun onAppear() {
        Logger.d("$simpleName: onAppear")
    }

    /**
     * onDismiss: Android.Activity.onStop()
     *            iOS.ViewController.viewWillDisappear()
     */
    protected open  fun onDismiss() {
        Logger.d("$simpleName: onDismiss")
    }

    /**
     * onActivate: Android.Activity.onResume()
     *             iOS.ViewController.viewDidAppear()
     */
    protected open  fun onActivate() {
        Logger.d("$simpleName: onActivate")
    }

    /**
     * onDeactivate: Android.Activity.onPause()
     *               iOS.ViewController.viewDidDisappear()
     */
    protected open  fun onDeactivate() {
        Logger.d("$simpleName: onDeactivate")
    }
    
```
# Troubleshoting
run "gradle wrapper" to solve the issue "Error: Could not find or load main class 

### Steps to update the configuration
More to come