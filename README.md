# jenkins-pipeline

Demo de **Jenkins + Pipeline as Code**: uma calculadora mínima em Python, testes com `unittest` e um `Jenkinsfile` que testa, gera o build e arquiva o artefato a cada push.

- `Jenkinsfile`: a esteira, versionada junto do código
- `jenkins-docker/`: Jenkins pronto para a demo (Python 3 + plugins de Pipeline)

```powershell
docker build -t jenkins-demo:lts jenkins-docker
docker run -d --name jenkins -p 8080:8080 -v jenkins_home:/var/jenkins_home jenkins-demo:lts
```
Depois: http://localhost:8080 → New Item → Pipeline → *Pipeline script from SCM* → este repositório.
