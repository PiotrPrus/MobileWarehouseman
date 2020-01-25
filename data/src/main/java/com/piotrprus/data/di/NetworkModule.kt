package com.piotrprus.data.di

import com.google.firebase.auth.FirebaseAuth
import com.piotrprus.data.provider.UserIdProvider
import com.piotrprus.data.provider.UserIdProviderImp
import org.koin.dsl.module

val networkModule = module {
    single { FirebaseAuth.getInstance() }
    single { UserIdProviderImp(get()) as UserIdProvider }
}