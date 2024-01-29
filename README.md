# StripePaymentProject : Java Spring Boot
StripePaymentProject is a project built using Strip API and Spring Boot, which creates, captures, refunds, and shows a list of payment intents.

## Prerequisites

Before running the application, ensure you have the following prerequisites:

1. Java Development Kit (JDK) installed.
2. Maven for building the Spring Boot project.

### API Endpoints

1. **Create Intent for Payment**
    - Endpoint: `POST /api/v1/create_intent`
    - Request Body: [paymentIntentDeatils](#paymentIntentDeatils)

2. **Capture the Created Intent**
    - Endpoint: `POST /api/v1/capture_intent/{id}`
    - Path Variable: `id` - ID of the created intent

3. **Create a Refund for the Created Intent**
    - Endpoint: `POST /api/v1/create_refund/{id}`
    - Path Variable: `id` - ID of the created intent

4. **Get a List of All Intents**
    - Endpoint: `GET /api/v1/get_intents`
  
#### Payment Details
```json
{
  "paymentIntentDeatils": "enter your payment details"
}
```

