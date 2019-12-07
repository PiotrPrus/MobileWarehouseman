package com.piotrprus.data.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val networkModule = module {
    single { FirebaseAuth.getInstance() }
}