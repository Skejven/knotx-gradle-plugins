/*
 * Copyright (C) 2019 Knot.x Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.knotx

val publishTask: TaskProvider<Task> = tasks.register("publish") {
    group = "composite-build"
}
val publishLocalTask: TaskProvider<Task> = tasks.register("publishToMavenLocal") {
    group = "composite-build"
}

subprojects {
    plugins.withId("maven-publish") {
        publishTask {
            dependsOn("${this@subprojects.path}:publish")
        }
        publishLocalTask {
            dependsOn("${this@subprojects.path}:publishToMavenLocal")
        }
    }
    plugins.withType<JavaPlugin> {
        publishTask {
            dependsOn(dependsOn("${this@subprojects.path}:test"))
        }
        publishLocalTask {
            dependsOn("${this@subprojects.path}:test")
        }
    }
}
