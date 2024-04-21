pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Construction du projet...'
                powershell 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Exécution des tests...'
                powershell 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Déploiement de l\'application...'
                powershell 'mvn spring-boot:run'
            }
        }
    }
}