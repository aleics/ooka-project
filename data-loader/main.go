package main

import (
	"bytes"
	"fmt"
	"net/http"
)

var usersData = [...]string{
	`
		{
			"id": "baac33b5-36e3-4a47-832c-e1d4edf19d79",
			"email": "peter.parker@example.com",
			"userName": "Peter Parker",
			"password": "1234",
			"accountType": "NORMAL",
			"userRole": "USER"
		}
	`,
	`
		{
			"id": "ef230d47-7abf-4115-9689-179ac10cc2b3",
			"email": "bruce.wayne@example.com",
			"userName": "Bruce Wayne",
			"password": "1234",
			"accountType": "NORMAL",
			"userRole": "ADMIN"
		}
	`,
	`
		{
			"id": "a9f1feec-8dea-4a8f-a8c2-c048461c8f4c",
			"email": "clark.kent@example.com",
			"userName": "Clark Kent",
			"password": "1234",
			"accountType": "NORMAL",
			"userRole": "USER"
		}
	`,
}

var productsData = [...]string{
	`name: \"Super Yacht XL\", description: \"Super Yacht XL is the perfect choice for nice trips in weekends and enjoy nights in the silent of the sea.\", price: 1200, available: true`,
	`name: \"Nice boat\", description: \"Nice boat is the perfect choice for nice trips in weekends and enjoy nights in the silent of the sea.\", price: 800, available: false`,
	`name: \"Sophisticated boat\", description: \"Sophisticated boat is the perfect choice for nice trips in weekends and enjoy nights in the silent of the sea.\", price: 900, available: true`,
	`name: \"Affordable boat\", description: \"Affordable boat is the perfect choice for nice trips in weekends and enjoy nights in the silent of the sea.\", price: 720, available: true`,
	`name: \"Regular boat\", description: \"Regular boat is the perfect choice for nice trips in weekends and enjoy nights in the silent of the sea.\", price: 600, available: false`,
	`name: \"Very nice boat\", description: \"Very nice boat is the perfect choice for nice trips in weekends and enjoy nights in the silent of the sea.\", price: 860, available: false`,
	`name: \"Yacht 1950\", description: \"Yacht 1950 is the perfect choice for nice trips in weekends and enjoy nights in the silent of the sea.\", price: 950, available: true`,
	`name: \"Super Mega Yacht 2000\", description: \"Super Mega Yacht 2000 is the perfect choice for nice trips in weekends and enjoy nights in the silent of the sea.\", price: 2000, available: true`,
	`name: \"Second hand boat\", description: \"Second hand boat is the perfect choice for nice trips in weekends and enjoy nights in the silent of the sea.\", price: 500, available: true`,
	`name: \"Cheap boat\", description: \"Cheap boat is the perfect choice for nice trips in weekends and enjoy nights in the silent of the sea.\", price: 450, available: false`,
}

func makeRequest(method, url string, data []byte, contentType, auth string) (http.Response, error) {
	req, err := http.NewRequest(method, url, bytes.NewBuffer(data))
	req.Header.Set("Content-Type", contentType)
	if auth != "" {
		req.Header.Set("Authorization", auth)
	}

	client := &http.Client{}
	resp, err := client.Do(req)
	if err != nil {
		return *resp, err
	}
	defer resp.Body.Close()

	return *resp, nil
}

func main() {
	token := ""
	url := "http://local.store.com/usermngmt/api/v1/login"
	data := []byte(`{ "email": "admin@example.com", "password": "admin" }`)

	resp, err := makeRequest("POST", url, data, "application/json", token)
	if err != nil {
		panic(err)
	}

	token = resp.Header.Get("Authorization")

	url = "http://local.store.com/usermngmt/api/v1/users"

	for _, userData := range usersData {
		resp, err := makeRequest("POST", url, []byte(userData), "application/json", token)
		if err != nil {
			panic(err)
		}
		if resp.StatusCode != 201 {
			fmt.Println("ERROR")
			fmt.Println(resp)
		}
		fmt.Println("User created")
	}

	url = "http://local.store.com/products/api/v1/graphql"

	for _, productData := range productsData {
		query := `
				{
					"query": "mutation { createProduct(` + productData + `) { id } }"
				}`

		resp, err := makeRequest("POST", url, []byte(query), "application/json", token)
		if err != nil {
			panic(err)
		}
		if resp.StatusCode != 200 {
			fmt.Println("ERROR")
			fmt.Println(resp)
		}
		fmt.Println("Product created")
	}
}
