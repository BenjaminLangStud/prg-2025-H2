// implement the prime logic in javascript:
/*

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

    */

function isPrime(num) {
    if (num < 2) {
        return false;
    }   
    for (let divisor = 2; divisor <= Math.sqrt(num); divisor++) {
        if (num % divisor === 0) {
            return false;
        }   
    }
    return true;
}
function getPrimesUpTo(n) {
    const primes = [];  
    for (let num = 2; num <= n; num++) {
        if (isPrime(num)) {
            primes.push(num);
        }   
    }
    return primes;
}

const n = 20
const primes = getPrimesUpTo(n);
console.log(`Prime numbers up to ${n}: ${primes}`);