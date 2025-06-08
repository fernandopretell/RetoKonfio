# 🐶 Reto Técnico Konfio

Una aplicación Android desarrollada en **Kotlin + Jetpack Compose + MVVM + Clean Architecture**, que muestra una lista de perros obtenida desde una API remota. El objetivo es demostrar arquitectura moderna, UI declarativa y manejo robusto de caché de datos con Room y Retrofit.

---

## 🧱 Tecnologías y Arquitectura

- **Jetpack Compose** para la UI.
- **MVVM + Clean Architecture** (sin capa de dominio intermedia en el MVP).
- **Retrofit + Kotlin Serialization** para la red.
- **Room** para persistencia local.
- **Hilt** para inyección de dependencias.
- **Accompanist / Material3** para compatibilidad visual moderna.
- **Kotlin Coroutines + State** para manejo reactivo.
- **JUnit + Compose Testing** para pruebas unitarias y UI.

---

## 🚀 Características

- Lista de perros obtenida desde la red.
- UI moderna y responsiva.
- Fallback seguro si no hay red disponible.
- Modo oscuro soportado.
- Pruebas unitarias y de UI con `FakeRepository`, `FakeUseCase`, y `TestViewModel`.

---

## 🔁 Política de Caché Híbrida (Remote + Room)

Para garantizar la **integridad de los datos** y evitar mostrar información obsoleta o duplicada, la app implementa una estrategia de caché híbrida:

1. **Primera carga o `forceRefresh = true`:**
    - Se consulta la API remota.
    - Si la respuesta es exitosa:
        - Se **limpia la base de datos local**.
        - Se insertan los nuevos datos en Room.
    - Si la red falla pero hay datos locales, se usan esos.
    - Si no hay red ni cache, se lanza excepción.

2. **Carga sin `forceRefresh`:**
    - Se lee directamente desde Room si hay datos.
    - Si Room está vacío, se consulta la red como fallback.

3. **Control de actualización inteligente:**
    - `CachePolicyManager` evalúa si debe forzarse un refresh en base a:
        - Tiempo desde la última actualización (`SharedPreferences`).
        - Estado de red (`NetworkMonitor`).
        - Petición explícita del usuario (pull-to-refresh).

Esto asegura que **los datos remotos prevalezcan cuando cambian**, pero sin penalizar la experiencia de usuario en caso de pérdida de conexión.

---

## 📂 Estructura de Carpetas

            app/
            ├── data/
            │ ├── local/
            │ │ ├── DogDao.kt
            │ │ └── DogEntity.kt
            │ ├── remote/
            │ │ ├── DogApiService.kt
            │ │ └── DogDto.kt
            │ ├── repository/
            │ │ └── DogRepositoryImpl.kt
            │ ├── mapper/
            │ │ └── DogMapper.kt
            │
            ├── domain/
            │ ├── usecase/
            │ │ └── GetDogsUseCase.kt
            │ ├── model/
            │ │ └── DogUi.kt
            │ ├── cache/
            │ │ └── CachePolicyManager.kt
            │
            ├── presentation/
            │ ├── doglist/
            │ │ ├── DogListScreen.kt
            │ │ ├── DogItem.kt
            │ │ └── DogListViewModel.kt
            │ ├── theme/
            │ │ ├── Theme.kt
            │ │ ├── Color.kt
            │ │ ├── Typography.kt
            │
            ├── di/
            │ ├── NetworkModule.kt
            │ ├── RepositoryModule.kt
            │ ├── DomainModule.kt
            │ ├── CoreModule.kt
            │
            ├── MainActivity.kt
            │
            ├── test/
            │ ├── fake/
            │ │ ├── FakeDogRepository.kt
            │ ├── DogRepositoryImplTest 
            │ ├── GetDogsUseCaseTest
            │
            ├── androidTest/
            │ └── DogItemTest

## 🧪 Pruebas

- Se cubren los siguientes casos:
    - Test de DogRepositoryImpl..
    - Test de GetDogsUseCase.
    - UI tests con Compose para `DogItem`, evitamos el test para `DogListScreen`, para no complicarnos con la inyeccion de dependencias del viewmodel en el test.

---


