@echo off
set BASE_DIR=C:\Users\kapildev\Desktop\try

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
    start /min cmd /c "cd /d %BASE_DIR%\%%D && mvn clean spring-boot:run"
    timeout /t 5 >nul
)

echo All services are starting...
pause
