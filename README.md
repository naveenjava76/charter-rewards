# Charter Spectrum Rewards Application
### End point : POST http://localhost:52415/v1/point_summary
### Sample Request(Valid) :
```json
[
    {
        "customer_id": "1234",
        "transaction_amount": 122,
        "transaction_date": "2023-01-10"
    },
    {
        "customer_id": "1234",
        "transaction_amount": 111,
        "transaction_date": "2023-02-12"
    },
    {
        "customer_id": "3456",
        "transaction_amount": 300.50,
        "transaction_date": "2023-02-09"
    },
    {
        "customer_id": "3456",
        "transaction_amount": 100,
        "transaction_date": "2023-02-10"
    },
    {
        "customer_id": "7896",
        "transaction_amount": -10,
        "transaction_date": "2022-12-15"
    }
]
```
### Sample Response(Success):
``` json
[
    {
        "customer_id": "3456",
        "points_by_month": [
            {
                "month": "FEBRUARY",
                "points": 501.0
            }
        ],
        "total_points": 501.0
    },
    {
        "customer_id": "1234",
        "points_by_month": [
            {
                "month": "FEBRUARY",
                "points": 72.0
            },
            {
                "month": "JANUARY",
                "points": 94.0
            }
        ],
        "total_points": 166.0
    },
    {
        "customer_id": "7896",
        "points_by_month": [
            {
                "month": "DECEMBER",
                "points": 0.0
            }
        ],
        "total_points": 0.0
    }
]
```

### Sample Request(InValid) :
```json
[
    {
        "transaction_amount": 122,
        "transaction_date": "2023-01-10"
    },
    {
        "customer_id": "1234",
        "transaction_amount": 111,
        "transaction_date": "2023-02-12"
    }
]
```
### Sample Response(Bad Request Error):
``` json
{
    "code": 400,
    "message": "Input transactions has null or blank customer id"
}
```
