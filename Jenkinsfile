// Pipeline as Code: este arquivo vive no Git, junto do código.
// Toda mudança na esteira passa por commit, histórico e code review.
pipeline {
  agent any

  // Gatilho: o Jenkins confere o repositório a cada minuto (sem webhook, pois roda em localhost)
  triggers { pollSCM('* * * * *') }

  options {
    timestamps()
    buildDiscarder(logRotator(numToKeepStr: '10'))
  }

  environment {
    VERSAO = "1.0.${BUILD_NUMBER}"
  }

  stages {
    stage('Checkout') {
      steps {
        echo "Código baixado do Git: commit ${env.GIT_COMMIT?.take(7)}"
      }
    }
    stage('Qualidade') {
      steps { sh 'python3 -m py_compile app.py test_app.py' }
    }
    stage('Testes') {
      steps { sh 'python3 -m unittest -v' }
    }
    stage('Build') {
      steps {
        sh '''
          mkdir -p dist
          cp app.py dist/
          echo "versao=${VERSAO}" > dist/VERSION.txt
        '''
      }
    }
  }

  post {
    success { archiveArtifacts artifacts: 'dist/**', fingerprint: true }
    failure { echo 'Pipeline falhou! Veja o stage em vermelho.' }
    always  { echo "Fim do build ${VERSAO}" }
  }
}
