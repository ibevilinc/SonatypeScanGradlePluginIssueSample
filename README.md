# Contents

* ***m2repository*** Precompiled maven repository with prebuilt archives
of *library*
* ***library*** Contains source to library module that produces the
contents of *m2repository*. The library is intentionally publishing
multiple variants (debug/release) as described in the android documentation
[here](https://developer.android.com/studio/publish-library/configure-pub-variants#multiple-pub-vars)
* ***app*** Contains source to project that consumes *library* module
from *m2repository*
* ***appModuleSubstitution*** Contains same source as *app* except that
it uses module substitution as described
[here](https://docs.gradle.org/current/userguide/resolution_rules.html#sec:dependency_substitution_rules)
* ***appIncludedBuild*** Contains same source as *app* except that it
uses a composite build as described
[here](https://docs.gradle.org/current/userguide/composite_builds.html)

# Observations

1. Running either **nexusIQIndex** or **nexusIQScan** gradle tasks on
*app* works as expected.
2. **nexusIQIndex** and **nexusIQScan** gradle tasks on *appModuleSubstitution* or *appIncludedBuild* fail with the
following error

```bash
./gradlew nexusIQIndex
> Task :nexusIQIndex FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':nexusIQIndex'.
> Could not resolve all dependencies for configuration ':app:sonatypeCopyConfiguration'.
   > Could not resolve com.example.libs:samplelib:1.0.0.
     Required by:
         project :app
      > The consumer was configured to find a runtime of a component. However we cannot choose between the following variants of project :library:samplelib:
          - debugRuntimeElements
          - releaseRuntimeElements
        All of them match the consumer attributes:
          - Variant 'debugRuntimeElements' capability com.example.libs:samplelib:1.0.0 declares a runtime of a component:
              - Unmatched attributes:
                  - Provides attribute 'com.android.build.api.attributes.AgpVersionAttr' with value '7.2.2' but the consumer didn't ask for it
                  - Provides attribute 'com.android.build.api.attributes.BuildTypeAttr' with value 'debug' but the consumer didn't ask for it
                  - Provides attribute 'com.android.build.gradle.internal.attributes.VariantAttr' with value 'debug' but the consumer didn't ask for it
                  - Provides a library but the consumer didn't ask for it
                  - Provides attribute 'org.gradle.jvm.environment' with value 'android' but the consumer didn't ask for it
                  - Provides attribute 'org.jetbrains.kotlin.platform.type' with value 'androidJvm' but the consumer didn't ask for it
          - Variant 'releaseRuntimeElements' capability com.example.libs:samplelib:1.0.0 declares a runtime of a component:
              - Unmatched attributes:
                  - Provides attribute 'com.android.build.api.attributes.AgpVersionAttr' with value '7.2.2' but the consumer didn't ask for it
                  - Provides attribute 'com.android.build.api.attributes.BuildTypeAttr' with value 'release' but the consumer didn't ask for it
                  - Provides attribute 'com.android.build.gradle.internal.attributes.VariantAttr' with value 'release' but the consumer didn't ask for it
                  - Provides a library but the consumer didn't ask for it
                  - Provides attribute 'org.gradle.jvm.environment' with value 'android' but the consumer didn't ask for it
                  - Provides attribute 'org.jetbrains.kotlin.platform.type' with value 'androidJvm' but the consumer didn't ask for it
        The following variants were also considered but didn't match the requested attributes:
          - Variant 'debugApiElements' capability com.example.libs:samplelib:1.0.0:
              - Incompatible because this component declares an API of a component and the consumer needed a runtime of a component
          - Variant 'releaseApiElements' capability com.example.libs:samplelib:1.0.0:
              - Incompatible because this component declares an API of a component and the consumer needed a runtime of a component

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.

* Get more help at https://help.gradle.org

Deprecated Gradle features were used in this build, making it incompatible with Gradle 8.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

See https://docs.gradle.org/7.5/userguide/command_line_interface.html#sec:command_line_warnings

BUILD FAILED in 908ms
1 actionable task: 1 executed
```
