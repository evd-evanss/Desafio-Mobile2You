package com.sayhitoiot.desafiomobile2you.features.details.presenter

import com.sayhitoiot.desafiomobile2you.features.details.interact.DetailsInteract
import com.sayhitoiot.desafiomobile2you.features.details.interact.DetailsInteractToPresenter
import com.sayhitoiot.desafiomobile2you.features.details.interact.DetailsPresenterToInteract

class DetailsPresenter(private val view: DetailsViewToPresenter) : DetailsPresenterToView,
    DetailsPresenterToInteract {

    private val interact: DetailsInteractToPresenter by lazy {
        DetailsInteract(this)
    }

}