package net.k40s;

import net.k40s.album.AlbumProductPage;
import net.k40s.album.AlbumsPage;
import net.k40s.single.SingleProductPage;
import net.k40s.single.SinglesPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Mah Tests
 */
public class TestMusicSite
{
	private WicketTester tester;
	PageParameters pageParameters;


	@Before
	public void setUp()
	{
		tester = new WicketTester(new MusicSite());
		pageParameters = new PageParameters();
		pageParameters.add("id", "2"); // Dunno why I use Hashes...
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		tester.startPage(HomePage.class);
		tester.assertRenderedPage(HomePage.class);
	}

	@Test
	public void renderEveryPage(){
		tester.startPage(HomePage.class);
		tester.assertRenderedPage(HomePage.class);
		tester.startPage(SinglesPage.class);
		tester.assertRenderedPage(SinglesPage.class);
		tester.startPage(AlbumsPage.class);
		tester.assertRenderedPage(AlbumsPage.class);
		tester.startPage(SingleProductPage.class, pageParameters);
		tester.assertRenderedPage(SingleProductPage.class);
		tester.startPage(AlbumProductPage.class, pageParameters);
		tester.assertRenderedPage(AlbumProductPage.class);
	}

	/**
	 * Note that this will fail, when you change the audio paths.
	 */
	@Test
	public void testPaths(){
		Assert.assertEquals(Storage.audioPath, "/var/audio/");
		Assert.assertEquals(Storage.relativeAudioPath, "/media");
	}

	@Test
	public void testLinksOnHomePage(){
		tester.startPage(HomePage.class);
		tester.assertRenderedPage(HomePage.class);
		tester.assertLabel("version", Storage.getVersion());
		tester.clickLink("singlesLink");
		tester.assertRenderedPage(SinglesPage.class);
		tester.clickLink("albumsLink");
		tester.assertRenderedPage(AlbumsPage.class);
		tester.clickLink("homeLink");
		tester.assertRenderedPage(HomePage.class);
	}

	@Test
	public void testMountedPages(){
		tester.executeUrl("./singles");
		tester.assertRenderedPage(SinglesPage.class);
		tester.executeUrl("./albums");
		tester.assertRenderedPage(AlbumsPage.class);
	}
}
