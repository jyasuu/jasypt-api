import requests
from ansible.errors import AnsibleFilterError

# Define the base URL of your Spring Boot application
BASE_URL = 'http://localhost:8080/api/encryption'  # Change if your server is on another host

def encrypt(value, password='test-salt'):
    """Encrypts the input string using the Spring Boot encryption API."""
    try:
        response = requests.post(
            f"{BASE_URL}/encrypt",
            params={'input': value,'password': password}
        )
        if response.status_code == 200:
            return response.text  # Encrypted string returned by the API
        else:
            raise AnsibleFilterError(f"Error encrypting value: {response.text}")
    except Exception as e:
        raise AnsibleFilterError(f"An error occurred while encrypting: {str(e)}")

def decrypt(value, password='test-salt'):
    """Decrypts the input string using the Spring Boot decryption API."""
    try:
        response = requests.post(
            f"{BASE_URL}/decrypt",
            params={'input': value,'password': password}
        )
        if response.status_code == 200:
            return response.text  # Decrypted string returned by the API
        else:
            raise AnsibleFilterError(f"Error decrypting value: {response.text}")
    except Exception as e:
        raise AnsibleFilterError(f"An error occurred while decrypting: {str(e)}")

class FilterModule(object):
    def filters(self):
        return {
            'encrypt_with_api': encrypt,
            'decrypt_with_api': decrypt
        }
