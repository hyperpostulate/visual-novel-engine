# visual-novel-engine

Spring Boot + Swing visual novel engine. Java 25, Maven.

## Build & test

```bash
./mvnw clean compile          # compile only
./mvnw test                   # run tests (single context-loads test)
./mvnw -B package             # build fat JAR
./mvnw spring-boot:run        # run the Swing GUI app
```

**Prerequisite:** JDK 25.

## Architecture

| Layer | Key class | Role |
|-------|-----------|------|
| Entry | `VisualNovelEngineApplication` | `@SpringBootApplication`, `CommandLineRunner`. **Must call `builder.headless(false)`** before `run()` — without this Swing will not start. |
| Orchestrator | `Director` | `@Component`. Sets FlatLaf dark L&F, calls `MainFrame.getInstance().initialize(...)`. |
| Window | `MainFrame` | Swing JFrame, manual singleton. Creates `Scene` panels. |
| Scene | `Scene` | JPanel using custom `RelativeLayout` (Y_AXIS): text 30%, image 60%, buttons 10%. |
| Story data | `StoryConfigFactory` | JAXB-deserializes `story.xml` into `StoryConfig` → `SceneConfig` → `ButtonConfig`. |

## Story config

`src/main/resources/story.xml` defines all scenes. Each `SceneConfig` has a headline, text body, image, and buttons with target scene IDs. Images loaded from `src/main/resources/images/{HeadLine}.gif`.

Properties in `src/main/resources/application.properties`:
- `app.width` / `app.height` — window size (800×600)
- `app.story-config-path` — defaults to `story.xml`
- `app.scene-images-dir` — defaults to `/images/`
- `app.image-file-postfix` — `.gif`

## Author

Mesut ORMANLI (mesutormanli@gmail.com)
