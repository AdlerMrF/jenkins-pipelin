// Cria um usuário admin LOCAL só para a demo (Jenkins em localhost).
// Credenciais de teste: usuário "admin", senha definida em JENKINS_ADMIN_PASSWORD (padrão: demo-jenkins-2026)
import jenkins.model.*
import hudson.security.*

def instance = Jenkins.get()
def senha = System.getenv('JENKINS_ADMIN_PASSWORD') ?: 'demo-jenkins-2026'
def realm = new HudsonPrivateSecurityRealm(false)
if (realm.getAllUsers().find { it.id == 'admin' } == null) {
  realm.createAccount('admin', senha)
}
instance.setSecurityRealm(realm)
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)
instance.save()
