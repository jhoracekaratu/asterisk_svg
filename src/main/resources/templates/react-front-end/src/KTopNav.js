function TopNav(){
    return(
    <>
     <nav className="navbar navbar-expand-lg navbar-light bg-light ">
     <div className="container-fluid">
        <a className="navbar-brand" href="">
        <img src="./logo.png" alt="" width="40" height="40" className="d-inline-block align-text-top"/>
            &nbsp;&nbsp;
        Javaguidings.com</a>
        <form className="d-flex">
      <button className="btn btn-outline-success" type="submit">Login</button>
    </form>
     </div>

     </nav>


       
    </>
    
    );
    }
    export default TopNav;