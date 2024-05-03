pipeline {
    agent any
      environment {
        SONAR_PROJECT_KEY = 'ColasRblr-Group'
        SONAR_HOST_URL = 'https://sonarcloud.io'
        SONAR_TOKEN = credentials('sonarQubeToken')
    }
    stages {
        stage('Build') {
            steps {
                echo 'Construction du projet:'
                powershell 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Exécution des tests:'
                powershell 'mvn test'
            }
        }
        stage('SonarQube Analysis:') {
            steps {
                echo 'Analyse SonarQube:'
                powershell "mvn sonar:sonar"
            }
        }
         stage('Deploy') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'develop' || env.BRANCH_NAME == 'main') {
                        echo 'Déploiement de l\'application'
                        powershell 'mvn spring-boot:run'
                    }
                }
            }
        }
    }
  post {
    always {
        junit 'my-reports/test-reports/*.xml'
        archiveArtifacts artifacts: 'my-reports/test-reports/*.xml', allowEmptyArchive: true
    }
    success {
        mail to: 'colas.rabiller@gmail.com',
                subject: 'Build succeeded',
                body: 'Build succeeded: you can go to this link for more informations : http://eft-holy-serval.ngrok-free.app/job/multibranch/'
    }
    failure {
        mail to: 'colas.rabiller@gmail.com',
                subject: 'Build failed',
                body: 'Build failed: you can go to this link for more informations : http://eft-holy-serval.ngrok-free.app/job/multibranch'
    }
 }
}