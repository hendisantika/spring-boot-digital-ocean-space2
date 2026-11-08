# spring-boot-digital-ocean-space2

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-digital-ocean-space2/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-digital-ocean-space2/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.x-6DB33F?logo=springboot)

A simple REST API built with Spring Boot to **upload, list, download, and delete files** on
[DigitalOcean Spaces](https://www.digitalocean.com/products/spaces) using the S3-compatible
[AWS SDK for Java v2](https://github.com/aws/aws-sdk-java-v2).

## Tech Stack

| Component        | Version              |
|------------------|----------------------|
| Java             | 25 (Temurin)         |
| Spring Boot      | 4.1.x                |
| AWS SDK (S3) v2  | 2.57.x               |
| Build tool       | Maven (via `./mvnw`) |
| Lombok           | managed by Boot      |

## Prerequisites

- JDK 25
- A DigitalOcean Space plus an access key / secret key
  (DigitalOcean Control Panel → **API** → **Spaces Keys**)

## Configuration

Properties live in `src/main/resources/application.yml`:

```yaml
do.space:
  access-key: your-access-key
  secret-key: your-secret-key
  endpoint: https://nyc3.digitaloceanspaces.com   # e.g. https://sgp1.digitaloceanspaces.com
  bucket-name: your-space-name
```

> ⚠️ **Never commit real credentials.** Keep the placeholders in `application.yml` and pass the real
> values at runtime, e.g. via environment variables (Spring relaxed binding):
>
> ```bash
> export DO_SPACE_ACCESSKEY=xxx
> export DO_SPACE_SECRETKEY=xxx
> export DO_SPACE_ENDPOINT=https://sgp1.digitaloceanspaces.com
> export DO_SPACE_BUCKETNAME=my-space
> ```
>
> or as command-line arguments: `--do.space.access-key=xxx --do.space.secret-key=xxx ...`

## Build & Run

```bash
# build and run tests
./mvnw clean verify

# run the application (http://localhost:8080)
./mvnw spring-boot:run
```

## REST API

Base path: `/files`

| Method   | Endpoint                     | Description                         |
|----------|------------------------------|-------------------------------------|
| `POST`   | `/files/upload`              | Upload a file (multipart `file`)    |
| `GET`    | `/files/list`                | List objects in the Space           |
| `GET`    | `/files/download/{fileName}` | Download a file as bytes            |
| `DELETE` | `/files/delete/{fileName}`   | Delete a file                       |

### Examples

```bash
# Upload
curl -F "file=@./photo.jpg" http://localhost:8080/files/upload

# List
curl http://localhost:8080/files/list

# Download
curl -o photo.jpg http://localhost:8080/files/download/photo.jpg

# Delete
curl -X DELETE http://localhost:8080/files/delete/photo.jpg
```

## CI/CD & Dependency Updates

- **GitHub Actions** (`.github/workflows/maven.yml`): builds and tests on JDK 25 (Temurin) for every
  push and pull request to `main`, then submits the dependency graph.
- **Dependabot** (`.github/dependabot.yml`):
  - Maven: daily at 05:00 Asia/Jakarta, grouped into `spring-boot`, `aws-sdk`, and `maven-minor-patch`
    PRs, with a short cooldown after new releases.
  - GitHub Actions: weekly (Monday), grouped into a single PR.
  - PRs are labeled, assigned, and use conventional commit prefixes (`build(deps)`, `ci(deps)`).

## Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: [@hendisantika34](https://t.me/hendisantika34)
- Link: [s.id/hendisantika](https://s.id/hendisantika)
