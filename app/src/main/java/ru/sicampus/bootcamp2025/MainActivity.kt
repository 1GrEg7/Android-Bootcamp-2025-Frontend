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
import ru.sicampus.bootcamp2025.ui.screens.AnotherProfileScreen
import ru.sicampus.bootcamp2025.ui.screens.AuthorizationScreen
import ru.sicampus.bootcamp2025.ui.screens.CentersScreen
import ru.sicampus.bootcamp2025.ui.screens.EditProfileScreen
import ru.sicampus.bootcamp2025.ui.screens.ListScreen
import ru.sicampus.bootcamp2025.ui.screens.MapScreen
import ru.sicampus.bootcamp2025.ui.screens.ProfileScreen
import ru.sicampus.bootcamp2025.ui.screens.RegistrationScreen

import ru.sicampus.bootcamp2025.ui.viewModels.EditProfileViewModel


  
import ru.sicampus.bootcamp2025.ui.viewModels.AnotherProfileViewModel
import ru.sicampus.bootcamp2025.ui.viewModels.CentersViewModel

import ru.sicampus.bootcamp2025.ui.viewModels.ListScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContent {
            val viewModelListScreen:ListScreenViewModel = viewModel()
            val editProfileViewModel: EditProfileViewModel = viewModel()
            val viewModelBuildComponents: BuildComponentsViewModel = viewModel()
            val centersViewModel:CentersViewModel = viewModel()

            val anotherProfileViewModel: AnotherProfileViewModel = viewModel()

            viewModelListScreen.getAllUsers()
            centersViewModel.getAllCenters()

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
                        bottomMenuViewModel = viewModelBuildComponents,
                        toAnotherProfileScreen = {navController.navigate(Screen.AnotherProfileScreen.route)},
                        toMapScreen = { navController.navigate(Screen.MapScreen.route)},
                        anotherProfileViewModel = anotherProfileViewModel,
                        toCentersScreen = { navController.navigate(Screen.CentersScreen.route) }
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
                        bottomMenuViewModel = viewModelBuildComponents,
                        vm = editProfileViewModel,
                        toMapScreen = { navController.navigate(Screen.MapScreen.route)}

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
                            navController.navigate(Screen.Profile.route)
                            viewModelBuildComponents.changeIconColorToWhite("profileScreen")
                        },
                        editProfileViewModel

                    )
                }

                composable(
                    Screen.AnotherProfileScreen.route,
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None },
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {

                    AnotherProfileScreen(
                        toListScreen = {
                            navController.navigate(Screen.ListScreen.route)
                            viewModelBuildComponents.changeIconColorToWhite("listScreen")
                        },
                        anotherProfileViewModel = anotherProfileViewModel
                    )
                }

                composable(
                    Screen.MapScreen.route,
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None },
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    MapScreen(
                        toMainScreen = {
                            navController.navigate(Screen.ListScreen.route)
                        },
                    )
                }

                composable(
                    Screen.CentersScreen.route,
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None },
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    CentersScreen(
                        toListScreen = {
                            navController.navigate(Screen.ListScreen.route)
                        },
                        centersViewModel = centersViewModel
                    )
                }



            }

        }
    }
}