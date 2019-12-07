package com.piotrprus.domain.di

import com.piotrprus.domain.usecase.FacebookSignInUseCase
import com.piotrprus.domain.usecase.UserLogoutUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { FacebookSignInUseCase(get()) }
    single { UserLogoutUseCase(get()) }
}