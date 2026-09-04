# Retro-Immersive Ultimate Fantasy — Fabric Mod (Minecraft 1.21.2 / Java 21)

## Por qué el `.jar` no viene incluido

Este proyecto fue generado en un entorno **sin acceso a internet**. Compilar
con Fabric Loom (`./gradlew remapJar`) necesita descargar, la primera vez:

- La distribución de Gradle 8.10.
- El jar de Minecraft 1.21.2 + mappings de Yarn.
- Fabric Loader y Fabric API.

Nada de eso se puede resolver sin red, así que el `.jar` tiene que salir de
**tu máquina**, no de acá.

## Opción sin instalar nada: GitHub Actions (compila solo)

Este proyecto ya trae `.github/workflows/build.yml`, que compila el mod
automáticamente en los servidores de GitHub (que sí tienen internet) cada
vez que subís código.

1. Creá un repositorio nuevo en GitHub (público o privado, da igual).
2. Subí el contenido de esta carpeta al repo:
   ```bash
   cd mod
   git init
   git add .
   git commit -m "Retro-Immersive Ultimate Fantasy - primer scaffold"
   git branch -M main
   git remote add origin https://github.com/TU_USUARIO/TU_REPO.git
   git push -u origin main
   ```
3. En GitHub, andá a la pestaña **Actions** del repo. Vas a ver correr el
   workflow "Build mod" automáticamente.
4. Cuando termine (ícono verde ✅), entrá a esa ejecución y bajá al final:
   ahí vas a tener un **artifact descargable** llamado
   `retro-immersive-ultimate-fantasy-jar`, que es un `.zip` con el `.jar`
   adentro. Ese es el mod compilado, listo para poner en tu carpeta `mods`.

Cada vez que subas cambios nuevos (`git push`), se vuelve a compilar solo y
te deja un `.jar` fresco. Si el build falla, la pestaña Actions te muestra
el error de compilación completo — mandámelo y lo arreglamos.

## Cómo compilarlo vos (alternativa local)

1. Instalá **JDK 21** (Temurin/Adoptium recomendado).
2. Si no tenés el Gradle wrapper local, generalo una vez con tu Gradle instalado:
   ```bash
   gradle wrapper --gradle-version 8.10
   ```
   (o abrí la carpeta con IntelliJ IDEA + plugin de Minecraft Development,
   que lo resuelve automáticamente).
3. Compilá:
   ```bash
   ./gradlew remapJar
   ```
4. El resultado queda en `build/libs/retroimmersiveultimatefantasy-1.0.0.jar`
   (el que va a la carpeta `mods` del cliente/servidor Fabric).

## Qué cumple este scaffold de los requisitos técnicos

1. **Paquete**: todo el código vive bajo `com.retroimmersive.ultimatefantasy`.
2. **`fabric.mod.json`**: `schemaVersion: 1`, `id: retroimmersiveultimatefantasy`,
   entrypoints `main`/`client` apuntando exactamente a las clases pedidas,
   y `depends` con `minecraft: ~1.21.2`, `fabricloader: >=0.16.0`, `java: >=21`.
3. **Aislamiento de classloading**: `RetroImmersiveUltimateFantasyClient` no
   tiene campos `static final` que instancien nada de renderizado, y todo
   `onInitializeClient()` está envuelto en `try-catch (Throwable t)`.
4. **Build**: `build.gradle` con Fabric Loom apuntando a 1.21.2, target Java 21.

## Progreso — Sección 2 (BTA), entrega 1/2

Implementado y funcional (no stub):
- Materiales: Azufre, Carbón de Acero, Lingote de Acero.
- Receta de fundición: Carbón de Acero → Lingote de Acero (horno vanilla).
- 3 sets de armadura completos (12 piezas): Acero, Cuero Reforzado, Cota de
  Malla — registrados con `ArmorMaterial` propio, agregados a la pestaña
  creativa del mod, con traducciones en `es_ar.json`/`en_us.json`.

Anotado como pendiente (no inventado, para no romper el build ni mentir
sobre el comportamiento):
- Resistencia a explosiones/caída/proyectiles por set: los valores de
  defensa/toughness ya reflejan la intención, pero el efecto *especial* por
  tipo de daño (ej. reducir daño de caída con Cuero Reforzado) necesita un
  mixin sobre `LivingEntity`, que va en la entrega de mecánicas de combate.

## Qué sigue

**Próxima entrega (Sección 2, parte 2/2):** Alto Horno de Fusión y Trommel
(bloques con inventario/GUI), Cañón Portátil, Carcaj y Flechas de Fuego
(requieren una entidad de proyectil propia).

**Después:** Sección 1 (biomas), Sección 3/4 (fauna y criaturas míticas),
Sección 8 (sistema social), Sección 9 (Compendio Arcano) — en ese orden,
como quedamos.

## Nota sobre riesgo de compilación

El material `ArmorMaterial`/`ArmorItem` cambió de forma varias veces entre
1.20.5 y 1.21.x. El patrón usado acá es el vigente para 1.21.2 según la
documentación disponible, pero si `./gradlew remapJar` te tira un error de
firma justo en `ModArmorMaterials.java` o `ModItems.registerArmor(...)`,
decímelo con el mensaje de error completo y lo ajusto al toque — es
localizado, no afecta al resto del mod.
