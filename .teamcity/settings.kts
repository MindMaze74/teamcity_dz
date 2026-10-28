import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2026.2"

project {

    vcsRoot(GitHubTeamcityDz)

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    artifactRules = "target/*.jar"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            id = "Maven2"

            conditions {
                equals("teamcity.build.branch", "master")
            }
            goals = "clean deploy"
            userSettingsSelection = "settings.xml"
        }
        maven {
            name = "Maven Test"
            id = "Maven_Test"

            conditions {
                doesNotEqual("teamcity.build.branch", "master")
            }
            goals = "clean test"
        }
    }
})

object GitHubTeamcityDz : GitVcsRoot({
    name = "GitHub teamcity_dz"
    url = "https://github.com/MindMaze74/teamcity_dz"
    branch = "master"
    branchSpec = "+:refs/heads/*"
    authMethod = password {
        userName = "public_repo"
        password = "credentialsJSON:6a70c1cc-8398-4750-ad19-21fec7ec09dc"
    }
})
