using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        Response.Clear();

        Response.ClearContent();

        Response.ClearHeaders();

        int productID = Convert.ToInt32(Request.QueryString["ProductID"]);

        string strSonuc = "";

        try

        {

            string conStr = "data source=LAPTOP-JN1G6OQ7\\SQLEXPRESS;database=Northwind;Integrated Security=True;";

            SqlConnection connection = new SqlConnection(conStr);

            connection.Open();

            string sorgu = "select p.ProductID,p.ProductName,c.CategoryName,p.UnitPrice from Products p join " +

            "Categories c on p.CategoryID = c.CategoryID where p.ProductID = @productID";

            SqlCommand cmd = new SqlCommand(sorgu, connection);

            cmd.Parameters.AddWithValue("@productID", productID);

            SqlDataReader reader = cmd.ExecuteReader();

            while (reader.Read())

            {

                var nes = new

                {

                    ProductID = reader["ProductID"].ToString(),

                    ProductName = reader["ProductName"].ToString(),

                    CategoryName = reader["CategoryName"].ToString(),

                    UnitPrice = reader["UnitPrice"].ToString()

                };

                strSonuc = new System.Web.Script.Serialization.JavaScriptSerializer().Serialize(nes);

            }

        }

        catch (Exception ex)

        {

            strSonuc = "Hata : " + ex.Message;

        }

        Response.Write(strSonuc);
    }
}