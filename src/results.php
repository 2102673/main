<?php
session_start();

if(isset($_GET['searchTerm'])) {
    $searchTerm = htmlspecialchars($_GET['searchTerm'], ENT_QUOTES, 'UTF-8');
} else {
    // Handle the case where no search term is provided
    $searchTerm = "No search term";
}

?>

<!DOCTYPE html>
<html>
<head>
    <title>Search Results</title>
</head>

<body>

    <div>
        <h1>Search Results</h1>
        <p>Search Term: <?php echo $searchTerm; ?></p>

        <form action="index.php" method="post">
            <div>
                <button type="submit">Return to Home Page</button>
            </div>
        </form>
    </div>
</body>

</html>
