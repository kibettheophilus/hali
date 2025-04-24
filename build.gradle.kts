import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ktlint)
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        reporters {
            reporter(ReporterType.JSON)
        }
        filter {
            exclude {
                projectDir.toURI().relativize(it.file.toURI()).path.contains("/generated/")
            }
            exclude {
                projectDir.toURI().relativize(it.file.toURI()).path.contains("/build/")
            }
        }
    }
}
