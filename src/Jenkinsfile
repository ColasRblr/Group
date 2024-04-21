pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Construction du projet...'
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Exécution des tests...'
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Déploiement de l\'application...'
                sh 'mvn spring-boot:run'
            }
        }
    }
}