def call(String distributionId, String awsAccessKeyId, String awsSecretAccessKey) {
    def awsCliCommand = """aws cloudfront create-invalidation --distribution-id $distributionId --paths '/*'"""
    def awsCliEnv = [AWS_ACCESS_KEY_ID: awsAccessKeyId, AWS_SECRET_ACCESS_KEY: awsSecretAccessKey]

    try {
        sh(script: awsCliCommand, returnStatus: true, environment: awsCliEnv)
        echo "Cache invalidation request sent to CloudFront."
    } catch (Exception e) {
        error "Failed to invalidate CloudFront cache: ${e}"
    }
}
