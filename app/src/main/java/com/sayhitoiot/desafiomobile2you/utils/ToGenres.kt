package com.sayhitoiot.desafiomobile2you.utils

enum class ToGenres constructor(val value : Int)
{

    Ação(28),
    Aventura(12),
    Animação(16),
    Comédia(35),
    Crime(80),
    Documentário(99),
    Drama(18),
    Familia(10751),
    Fantasia(14),
    História(36),
    Horror(27),
    Música(10402),
    Mistério(9648),
    Romance(10749),
    Ficção_Científica(10749),
    FIlme_de_TV(878),
    Suspense(53),
    Guerra(10752),
    Ocidental(37);

    companion object {
        operator fun invoke(rawValue: Int) = values().find {
            it.value == rawValue
        }.toString().replace("_", " ")
    }

}