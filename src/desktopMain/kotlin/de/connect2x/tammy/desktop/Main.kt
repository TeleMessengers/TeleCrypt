package de.connect2x.tammy.desktop

import de.connect2x.messenger.desktop.startMessenger
import de.connect2x.tammy.tammyConfiguration

fun main(args: Array<String>) = startMessenger(
    configuration = tammyConfiguration(),
    args = args,
)