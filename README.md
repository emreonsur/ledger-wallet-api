# Ledger & Wallet API

A university project implementing a Ledger & Wallet REST API using Java 17, Spring Boot 3, and MySQL 8.

## Features

- Create wallet
- Get all wallets
- Get wallet by ID
- Deposit money
- Withdraw money
- Transfer money between wallets
- Get wallet transaction history
- Get all transactions
- Validation and error handling

## Tech Stack

- Java 17
- Spring Boot 3
- MySQL 8
- Maven
- Spring Data JPA
- Jakarta Validation

## Project Structure

- `controller` — REST endpoints
- `service` — business logic
- `repository` — database access
- `entity` — JPA entities
- `dto` — request DTOs
- `exception` — global and custom exceptions

## Main Endpoints

- `POST /api/wallets`
- `GET /api/wallets`
- `GET /api/wallets/{id}`
- `POST /api/wallets/{id}/deposit`
- `POST /api/wallets/{id}/withdraw`
- `POST /api/transfers`
- `GET /api/wallets/{id}/transactions`
- `GET /api/transactions`

## How to Run

1. Create a MySQL database named `ledger_wallet_api`
2. Configure `application.properties`
3. Run the Spring Boot application
4. Test endpoints with Postman

## Status

Core API implementation is complete and manually tested.