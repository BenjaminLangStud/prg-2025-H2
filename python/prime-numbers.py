
# calculates a prime number
def is_prime(num):
    if num < 2:
        return False
    for divisor in range(2, int(num**0.5) + 1):
        if num % divisor == 0:
            return False
    return True

if __name__ == "__main__":
    n = int(input("Enter a number: "))
    primes = []
    for num in range(2, n + 1):
        if is_prime(num):
            primes.append(num)
    print(f"Prime numbers up to {n}: {primes}")