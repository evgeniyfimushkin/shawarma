http://localhost:9000/oauth2/authorize?response_type=code&client_id=admin&redirect_uri=http://127.0.0.1:9090/login/oauth2/code/admin&scope=writeIngredients+deleteIngredients

curl localhost:9000/oauth2/token \
-H"Content-type: application/x-www-form-urlencoded" \
-d "grant_type=authorization_code" \
-d"code=K6327l_gfM6Xo_akqapZ8b-wntWHCVKvJQfHYMJuO4jlbQWpNSYaT_qISev6hA78qfTFyj0iQWpnXUDysbN7BYwQqbl2M0S9FB-mmYK1m7DBVLJCpW5cSouqm26inxo3" \
-d"redirect_uri=http://127.0.0.1:9090/login/oauth2/code/admin" \
-u admin:admin

curl localhost:9000/oauth2/token \
-H "Content-type: application/x-www-form-urlencoded" \
-d "grant_type=refresh_token&refresh_token=hgc9bJXOwHpke-ASbTzQkUof6ftMJyNIO6pGxD3j8zl0K2wckXRd5xSTPLwALn0ys2gMANAV2LaNR1DXbxmm-D1NkOfAJbG2wHqJQj9wmXfcHscbAWA-RKE_mPJnr8zf" \
-u admin:admin