package com.theokibet.hali.data.network

val dailyResponse = """
        {
            "latitude": 35.7,
            "longitude": 139.75,
            "generationtime_ms": 0.11932849884033203,
            "utc_offset_seconds": 32400,
            "timezone": "Asia/Tokyo",
            "timezone_abbreviation": "GMT+9",
            "elevation": 12.0,
            "daily_units": {
                "time": "iso8601",
                "weathercode": "wmo code",
                "temperature_2m_max": "°C",
                "temperature_2m_min": "°C"
            },
            "daily": {
                "time": [
                    "2025-04-24",
                    "2025-04-25",
                    "2025-04-26",
                    "2025-04-27",
                    "2025-04-28",
                    "2025-04-29",
                    "2025-04-30",
                    "2025-05-01"
                ],
                "weathercode": [
                    3,
                    3,
                    51,
                    3,
                    3,
                    2,
                    0,
                    1
                ],
                "temperature_2m_max": [
                    22.4,
                    22.2,
                    20.3,
                    19.2,
                    19.7,
                    18.3,
                    20.0,
                    20.7
                ],
                "temperature_2m_min": [
                    14.8,
                    15.6,
                    12.1,
                    9.4,
                    16.0,
                    11.8,
                    11.1,
                    15.0
                ]
            }
        }
    """.trimIndent()

val errorResponse = """
    {
        "error": true,
        "reason": "Invalid timezone"
    }
""".trimIndent()

val hourlyResponse = """
    {
        "latitude": 35.7,
        "longitude": 139.75,
        "generationtime_ms": 0.03814697265625,
        "utc_offset_seconds": 32400,
        "timezone": "Asia/Tokyo",
        "timezone_abbreviation": "GMT+9",
        "elevation": 12.0,
        "hourly_units": {
            "time": "iso8601",
            "temperature_2m": "°C",
            "precipitation": "mm",
            "weathercode": "wmo code"
        },
        "hourly": {
            "time": [
                "2025-04-24T00:00",
                "2025-04-24T01:00",
                "2025-04-24T02:00",
                "2025-04-24T03:00",
                "2025-04-24T04:00",
                "2025-04-24T05:00",
                "2025-04-24T06:00",
                "2025-04-24T07:00",
                "2025-04-24T08:00",
                "2025-04-24T09:00",
                "2025-04-24T10:00",
                "2025-04-24T11:00",
                "2025-04-24T12:00",
                "2025-04-24T13:00",
                "2025-04-24T14:00",
                "2025-04-24T15:00",
                "2025-04-24T16:00",
                "2025-04-24T17:00",
                "2025-04-24T18:00",
                "2025-04-24T19:00",
                "2025-04-24T20:00",
                "2025-04-24T21:00",
                "2025-04-24T22:00",
                "2025-04-24T23:00"
            ],
            "temperature_2m": [
                16.0,
                15.9,
                15.7,
                15.6,
                15.2,
                15.2,
                15.9,
                16.1,
                16.6,
                17.2,
                18.7,
                20.0,
                22.0,
                22.8,
                23.5,
                23.1,
                21.8,
                20.5,
                19.0,
                18.4,
                17.9,
                17.0,
                16.3,
                15.9
            ],
            "precipitation": [
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00,
                0.00
            ],
            "weathercode": [
                3,
                3,
                3,
                3,
                3,
                3,
                3,
                2,
                2,
                2,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                2,
                2,
                2,
                2,
                3,
                3
            ]
        }
    }
""".trimIndent()