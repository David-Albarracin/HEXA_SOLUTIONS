import subprocess
import os

def start_backend():
    # Cambiar al directorio del backend y ejecutar el servidor Spring Boot
    backend_dir = os.path.join(os.getcwd(), 'backend')
    # Abre una nueva consola y mantiene la ventana abierta
    subprocess.Popen(['start', 'cmd', '/K', 'cd "{}" && ./mvnw spring-boot:run'.format(backend_dir)], shell=True)

def start_frontend():
    # Cambiar al directorio del frontend y ejecutar el servidor Angular
    frontend_dir = os.path.join(os.getcwd(), 'frontend')
    # Abre una nueva consola y mantiene la ventana abierta
    subprocess.Popen(['start', 'cmd', '/K', 'cd "{}" && ng serve'.format(frontend_dir)], shell=True)

if __name__ == '__main__':
    start_backend()
    start_frontend()
    print("Backend y frontend están corriendo en consolas separadas...")
