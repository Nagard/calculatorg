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
				bat "docker build -t leszko/calculator ."
			}
		}
		
		stage("Docker push") {
			steps {
				bat "docker push leszko/calculator"
			}
		}
		
		stage("Deploy to staging") {
			steps {
				bat "docker run -d --rm -p 8765:8080 --name calculator leszko/calculator"
			}
		}
		
		stage("Acceptance test") {
			steps {
				sleep 5
				bat "acceptance_test.sh"
			}
		}
		
				
	}
	
	post {
		always {
			bat "docker stop calculator"
		}
	}	
	
}