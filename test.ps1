# SpringBootDemo API Test Script
# Tests all endpoints of the Spring Boot 4.0.7 REST API

# Assume the app is running on http://localhost:8080

Write-Host "=== SpringBootDemo API Test Script ===" -ForegroundColor Cyan

# Test 1: Get all customers
Write-Host "`n[1] GET /customers - Get all customers" -ForegroundColor Yellow
try {
    $customers = Invoke-RestMethod http://localhost:8080/customers
    Write-Host "Response: $($customers | ConvertTo-Json)" -ForegroundColor Green
} catch {
    Write-Host "Error: $_" -ForegroundColor Red
}

# Test 2: Create a customer
Write-Host "`n[2] POST /create - Create a customer" -ForegroundColor Yellow
try {
    $body = @{
        firstName = "Alice"
        lastName = "Walker"
        accountNumber = "A100200"
    } | ConvertTo-Json
    
    Write-Host "Request Body: $body" -ForegroundColor Cyan
    
    $response = Invoke-RestMethod -Method Post `
        -Uri http://localhost:8080/create `
        -ContentType 'application/json' `
        -Body $body `
        -ErrorAction Stop
    
    Write-Host "Response: $($response | ConvertTo-Json)" -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
    if ($_.ErrorDetails) {
        Write-Host "Error Details: $($_.ErrorDetails.Message)" -ForegroundColor Red
    }
    if ($_ | Get-Member -Name Exception -ErrorAction SilentlyContinue) {
        if ($_.Exception -is [System.Net.Http.HttpRequestException]) {
            Write-Host "HTTP Error" -ForegroundColor Red
        }
    }
}

# Test 3: Get home endpoint
Write-Host "`n[3] GET / - Home endpoint" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod http://localhost:8080/
    Write-Host "Response: $response" -ForegroundColor Green
} catch {
    Write-Host "Error: $_" -ForegroundColor Red
}

# Test 4: Get greeting endpoint
Write-Host "`n[4] GET /greeting - Greeting endpoint" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod http://localhost:8080/greeting
    Write-Host "Response: $response" -ForegroundColor Green
} catch {
    Write-Host "Error: $_" -ForegroundColor Red
}

# Test 5: Health check
Write-Host "`n[5] GET /actuator/health - Health check" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod http://localhost:8080/actuator/health
    Write-Host "Response: $($response | ConvertTo-Json)" -ForegroundColor Green
} catch {
    Write-Host "Error: $_" -ForegroundColor Red
}

Write-Host "`n=== Test Complete ===" -ForegroundColor Cyan
