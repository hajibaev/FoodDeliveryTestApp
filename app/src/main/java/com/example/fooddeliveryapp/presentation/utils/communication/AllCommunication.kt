package com.example.fooddeliveryapp.presentation.utils.communication

import com.example.fooddeliveryapp.presentation.utils.event.Event
import com.example.fooddeliveryapp.presentation.utils.navigation.NavigationCommand


interface NavigationCommunication : Communication<Event<NavigationCommand>> {
    class Base : Communication.Base<Event<NavigationCommand>>(), NavigationCommunication
}
