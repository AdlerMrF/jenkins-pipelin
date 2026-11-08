# Roteiro da demo — Jenkins

## Antes da aula
1. Docker Desktop aberto.
2. `docker pull jenkins/jenkins:lts`
3. Criar repositório público `jenkins-demo` no GitHub (branch `main`) com `app.py`, `test_app.py` e `Jenkinsfile` desta pasta:
   ```powershell
   git init -b main
   git add .
   git commit -m "Projeto demo Jenkins"
   git remote add origin https://github.com/SEU-USUARIO/jenkins-demo.git
   git push -u origin main
   ```

## 1. Subir o Jenkins (PowerShell)
```powershell
docker run -d --name jenkins -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts
docker exec -u root jenkins bash -c "apt-get update && apt-get install -y python3"
docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

## 2. Configuração inicial
http://localhost:8080 → colar senha → Install suggested plugins → criar admin → Start using Jenkins.

## 3. Criar o job
New Item → `demo-pipeline` → Pipeline → OK
- Definition: Pipeline script from SCM
- SCM: Git
- Repository URL: https://github.com/SEU-USUARIO/jenkins-demo.git
- Branch Specifier: `*/main`
- Script Path: `Jenkinsfile`
- Save → Build Now

## 4. Rodar, quebrar, consertar
1. Build verde → mostrar Console Output e artefato `dist/`.
2. Em `app.py` trocar `a + b` por `a - b`, commit + push, Build Now → stage Testes vermelho (`AssertionError: -1 != 5`).
3. Voltar para `a + b`, push, Build Now → verde.

Bônus: adicionar `triggers { pollSCM('* * * * *') }` abaixo de `agent any` → build dispara sozinho a cada push.

## Testar localmente (opcional)
```powershell
python -m unittest -v
```

## Limpar depois
```powershell
docker rm -f jenkins
docker volume rm jenkins_home
```
