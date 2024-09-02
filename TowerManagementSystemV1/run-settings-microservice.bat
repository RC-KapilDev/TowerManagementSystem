@echo off
set BASE_DIR=C:/Users/Verizon1/Desktop/ttm-main/TowerManagementSystem/TowerManagementSystemV1

for %%D in (
    sertry
    api-gateway
    config-server
    equipmentservice
    maintenanceservice
    notificationservice
    tower
    usermicroservice
    workorderservice
) do (
    echo Starting %%D...
    start /min cmd /c "cd /d %BASE_DIR%\%%D && mvn  spring-boot:run"
    timeout /t 5 >nul
)

echo All services are starting...
pause
