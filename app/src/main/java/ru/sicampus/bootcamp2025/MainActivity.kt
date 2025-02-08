package ru.sicampus.bootcamp2025

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import ru.sicampus.bootcamp2025.ui.buildComponents.BuildComponentsViewModel
import ru.sicampus.bootcamp2025.ui.navigation.Screen
import ru.sicampus.bootcamp2025.ui.screens.AuthorizationScreen
import ru.sicampus.bootcamp2025.ui.screens.EditProfileScreen
import ru.sicampus.bootcamp2025.ui.screens.ListScreen
import ru.sicampus.bootcamp2025.ui.screens.ProfileScreen
import ru.sicampus.bootcamp2025.ui.screens.RegistrationScreen
import ru.sicampus.bootcamp2025.ui.viewModels.ListScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)





        setContent {

            val viewModelListScreen:ListScreenViewModel = viewModel()

            val viewModelBuildComponents: BuildComponentsViewModel = viewModel()

            viewModelListScreen.getAllUsers()

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.AuthorizationScreen.route
            ) {
                composable(
                    Screen.RegistrationScreen.route,
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None },
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {

                    RegistrationScreen(
                        onRegistre = { navController.navigate(Screen.ListScreen.route)},
                        onBack = { navController.popBackStack() },
                    )
                }
                composable(
                    Screen.AuthorizationScreen.route,
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None },
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    AuthorizationScreen(
                        toRegistreScreen = {
                            navController.navigate(Screen.RegistrationScreen.route)
                                           },
                        onSignIn = { navController.navigate(Screen.ListScreen.route) }
                    )
                }

                composable(
                    Screen.ListScreen.route,
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None },
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    ListScreen(

                        toAuthorizationScreen = {
                            navController.navigate(Screen.AuthorizationScreen.route)
                                                },
                        toProfileScreen = {
                            navController.navigate(Screen.Profile.route)
                            viewModelBuildComponents.changeIconColorToWhite("profileScreen")
                                          },
                        toListScreen = {
                            navController.navigate(Screen.ListScreen.route)
                            viewModelBuildComponents.changeIconColorToWhite("listScreen")
                                       },
                        //toMapScreen = {navController.navigate(Screen.ListScreen.route)},
                        vm = viewModelListScreen,
                        bottomMenuViewModel = viewModelBuildComponents
                    )
                }

                composable(
                    Screen.Profile.route,
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None },
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                    ) {
                    ProfileScreen(
                        onEditClick = { navController.navigate(Screen.EditProfile.route) } ,
                        toListScreen = {
                            navController.navigate(Screen.ListScreen.route)
                            viewModelBuildComponents.changeIconColorToWhite("listScreen")
                                       },
                        toAuthorizationScreen = {
                            navController.navigate(Screen.AuthorizationScreen.route)
                                                },
                        toProfileScreen = {
                            navController.navigate(Screen.Profile.route)
                            viewModelBuildComponents.changeIconColorToWhite("profileScreen")
                                          },
                        bottomMenuViewModel = viewModelBuildComponents
                       // toMapScreen = {}
                    )
                }
                composable(
                    Screen.EditProfile.route,
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None },
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    EditProfileScreen(
                        onCancel = { navController.navigate(Screen.Profile.route) },
                        onSave = { navController.popBackStack() },
                        toProfileScreen =
                        {
                            navController.navigate(Screen.Profile.route);
                            viewModelBuildComponents.changeIconColorToWhite("profileScreen")
                        },

                    )
                }


            }

        }
    }
}