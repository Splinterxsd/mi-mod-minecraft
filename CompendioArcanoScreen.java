package com.retroimmersive.ultimatefantasy.client.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GUI del Compendio del Explorador Arcano (Sección 9): pestañas, buscador,
 * modelos 3D rotatorios del Bestiario.
 * <p>
 * Aislamiento de classloading: esta clase deliberadamente NO tiene ningún
 * campo {@code static final} que instancie {@code net.minecraft.client.gui.screen.Screen}
 * ni ningún elemento visual. {@link #registerHandler()} solo registra un
 * proveedor/callback; la pantalla real se construye recién cuando el
 * jugador abre el ítem del libro, momento en el que el classloader de
 * cliente ya está completamente cargado.
 */
public final class CompendioArcanoScreen {
	private static final Logger LOGGER = LoggerFactory.getLogger("RetroImmersiveUltimateFantasy/CompendioArcano");

	private CompendioArcanoScreen() {}

	public static void registerHandler() {
		LOGGER.info("Registrando handler del Compendio del Explorador Arcano (carga perezosa)...");
		// TODO: registrar un ItemStorage/UseCallback que, al ejecutarse,
		// haga `MinecraftClient.getInstance().setScreen(new CompendioArcanoScreen(...))`
		// — la construcción de la screen ocurre en tiempo de uso, no acá.
	}
}
