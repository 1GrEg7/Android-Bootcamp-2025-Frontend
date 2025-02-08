package ru.sicampus.bootcamp2025.ui.navigation

sealed class Screen(val route: String) {
    object Profile : Screen("profile")
    object EditProfile : Screen("editProfile")
    object MapScreen: Screen("mapScreen")
    object AuthorizationScreen : Screen("authorizationScreen")
    object ListScreen : Screen("listScreen")
    object RegistrationScreen : Screen("registrationScreen")
    object AnotherProfileScreen : Screen("anotherProfileScreen")
    object CentersScreen : Screen("centersScreen")

}
