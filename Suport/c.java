#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_PRODUCTS 100

struct Product {
    char name[50];
    double price;
    int available;
};

struct Product products[MAX_PRODUCTS];
int num_products = 0;

void add_product() {
    if (num_products == MAX_PRODUCTS) {
        printf("Error: Maximum number of products exceeded\n");
        return;
    }

    struct Product new_product;

    printf("Enter product name: ");
    scanf("%s", new_product.name);

    printf("Enter product price: ");
    scanf("%lf", &new_product.price);

    printf("Enter number of available products: ");
    scanf("%d", &new_product.available);

    products[num_products++] = new_product;

    printf("Product added successfully!\n");
}

void update_price() {
    char name[50];
    printf("Enter product name: ");
    scanf("%s", name);

    int i;
    for (i = 0; i < num_products; i++) {
        if (strcmp(products[i].name, name) == 0) {
            printf("Enter new price: ");
            scanf("%lf", &products[i].price);
            printf("Price updated successfully!\n");
            return;
        }
    }

    printf("Error: Product not found\n");
}

void list_products() {
    printf("Available products:\n");
    printf("%-30s %-10s %-10s\n", "Product Name", "Price", "Available");
    int i;
    for (i = 0; i < num_products; i++) {
        printf("%-30s $%-9.2lf %-10d\n", products[i].name, products[i].price, products[i].available);
    }
}

int compare_products(const void *p1, const void *p2) {
    const struct Product *product1 = p1;
    const struct Product *product2 = p2;

    if (product1->price < product2->price) {
        return -1;
    } else if (product1->price > product2->price) {
        return 1;
    } else {
        return 0;
    }
}

void sort_products() {
    qsort(products, num_products, sizeof(struct Product), compare_products);
    printf("Products sorted by price in ascending order!\n");
}

void print_products() {
    printf("All products:\n");
    printf("%-30s %-10s %-10s\n", "Product Name", "Price", "Available");
    int i;
    for (i = 0; i < num_products; i++) {
        printf("%-30s $%-9.2lf %-10d\n", products[i].name, products[i].price, products[i].available);
    }
}

int main() {
    int choice;
    do {
        printf("Prescription Orders Management System\n");
        printf("-------------------------------------\n");
        printf("1. Add a new product\n");
        printf("2. Update price for a particular product\n");
        printf("3. List all available products\n");
        printf("4. Sort all products by product price as ascending\n");
        printf("5. Print information of all products\n");
        printf("6. Exit\n");
        printf("Enter your choice (1-6): ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                add_product();
                break;
            case 2:
                update_price();
                break;
            case 3:
                list_products();
                break;
            case 4:
                sort_products();
                break;
            case 5:
                print_products();
                break;
            case 6:
                printf("Exiting...\n");
                break;
            default:
                printf("Invalid choice\n");
        }

        printf("\n");
    } while (choice != 6);

    return 0;
}
