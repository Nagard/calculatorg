pipeline {
	agent any
	
	
	stages {
		stage("Compile") {
			steps {
				bat "gradlew compileJava"
			}
		}
		stage("Unit test") {
			steps {
				bat "gradlew test"
			}
		}
		
		stage("Code coverage") {
			steps {
				bat "gradlew jacocoTestReport"
				publishHTML (target: [
					reportDir: 'build/reports/jacoco/test/html',
					reportFiles: 'index.html',
					reportName: "JaCoCo Report" ])
				bat "gradlew jacocoTestCoverageVerification"
			}
		}

		stage("Static code analysis") {
			steps {
				bat "gradlew checkstyleMain"
				publishHTML (target: [
					reportDir: 'build/reports/checkstyle/',
					reportFiles: 'main.html',
					reportName: "Checkstyle Report" ])
			}
		}
		
		stage("Package") {
			steps {
				bat "gradlew build"
			}
		}
		
		
  

		stage("Docker build") {
			steps {
				bat "docker build -t dm4711/calculator ."
			}
		}
		
		stage("Docker push") {
			steps {
				bat "docker login --username dm4711 --password securedm4711"
				bat "docker push dm4711/calculator"
				
			}
		}
		
		stage("Deploy to staging") {
			steps {
				bat "docker-compose up-d"
			}
		}
		
		stage("Acceptance test") {
			steps {
				sleep 60
				bat "acceptance_test.sh"
			}
		}
		
				
	}
	
	post {
		always {
			bat "docker-compose down"
		}
	}	
	
}